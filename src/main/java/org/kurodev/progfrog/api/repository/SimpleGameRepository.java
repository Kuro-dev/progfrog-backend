package org.kurodev.progfrog.api.repository;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.script.JavaScriptManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component("ProgFrogCacheBean")
public class SimpleGameRepository implements GameRepository {
    private static final Logger logger = LoggerFactory.getLogger(SimpleGameRepository.class);
    private final Map<String, RepoItem<ProgFrogGame>> gameCache = new HashMap<>();
    private final Map<String, RepoItem<JavaScriptManager>> scriptCache = new HashMap<>();

    public SimpleGameRepository() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("cache-cleanup-thread");
            return t;
        });
        executorService.scheduleWithFixedDelay(this::pruneCaches, 0, 1, TimeUnit.HOURS);
    }

    private void pruneCaches() {
        int before = gameCache.keySet().size();
        gameCache.entrySet().stream()
                .filter(entry -> entry.getValue().isDirty())
                .forEach(entry -> gameCache.remove(entry.getKey()));
        int after = gameCache.keySet().size();
        logger.info("Removed {} chached games", before - after);

        before = scriptCache.keySet().size();
        scriptCache.entrySet().stream()
                .filter(stringRepoItemEntry -> stringRepoItemEntry.getValue().isDirty())
                .forEach(entry -> scriptCache.remove(entry.getKey()));
        after = scriptCache.keySet().size();
        logger.info("Removed {} cached scripts", before - after);
    }

    @Override
    public String getUniqueID() {
        String id = UUID.randomUUID().toString();
        while (gameCache.containsKey(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    @Override
    public String storeGame(String id, ProgFrogGame game) {
        gameCache.put(id, new RepoItem<>(game));
        return id;
    }

    @Override
    public String storeScript(String id, JavaScriptManager script) {
        scriptCache.put(id, new RepoItem<>(script));
        return id;
    }

    @Override
    @Cacheable("games")
    public Optional<ProgFrogGame> findGameByID(String id) {
        var result = gameCache.get(id);
        if (result == null) {
            return Optional.empty();
        }
        result.lastModified = System.currentTimeMillis();
        return Optional.of(result.item);
    }

    @Override
    @Cacheable("scripts")
    public Optional<JavaScriptManager> findScriptById(String id) {
        var result = scriptCache.get(id);
        if (result == null) {
            return Optional.empty();
        }
        result.lastModified = System.currentTimeMillis();
        return Optional.of(result.item);
    }

    private static final class RepoItem<T> {
        private final long created;
        private final T item;
        private long lastModified;

        private RepoItem(long created, long lastModified, T item) {
            this.created = created;
            this.lastModified = lastModified;
            this.item = item;
        }

        private RepoItem(T item) {
            this(System.currentTimeMillis(), System.currentTimeMillis(), item);
        }

        private boolean isDirty() {
            return lastModified - created > TimeUnit.HOURS.toMillis(1);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (RepoItem) obj;
            return this.created == that.created &&
                    this.lastModified == that.lastModified &&
                    Objects.equals(this.item, that.item);
        }

        @Override
        public int hashCode() {
            return Objects.hash(created, lastModified, item);
        }

        @Override
        public String toString() {
            return "RepoItem[" +
                    "created=" + created + ", " +
                    "lastModified=" + lastModified + ", " +
                    "item=" + item + ']';
        }

    }
}
