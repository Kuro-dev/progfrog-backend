package org.kurodev.progfrog.api.repository;

import org.kurodev.progfrog.game.ProgFrogGame;
import org.kurodev.progfrog.game.map.MapEditor;
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
    private final Map<String, RepoItem<MapEditor>> editorCache = new HashMap<>();

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
        logger.info("Removed {} cached games", pruneCache(gameCache));
        logger.info("Removed {} cached scripts", pruneCache(scriptCache));
        logger.info("Removed {} cached Map editor instances", pruneCache(editorCache));
    }

    private <T> int pruneCache(Map<String, RepoItem<T>> cache) {
        int before = gameCache.keySet().size();
        cache.entrySet().stream()
                .filter(entry -> entry.getValue().isDirty())
                .forEach(entry -> cache.remove(entry.getKey()));
        int after = gameCache.keySet().size();
        return before - after;
    }

    private boolean idExists(String id) {
        return gameCache.containsKey(id)
                || scriptCache.containsKey(id)
                || editorCache.containsKey(id);
    }

    @Override
    public String getUniqueID() {
        String id = UUID.randomUUID().toString();
        while (idExists(id)) {
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

    @Override
    @Cacheable("map-editors")
    public Optional<MapEditor> findEditorById(String id) {
        var result = editorCache.get(id);
        if (result == null) {
            return Optional.empty();
        }
        result.lastModified = System.currentTimeMillis();
        return Optional.of(result.item);
    }

    @Override
    public String storeEditor(MapEditor editor) {
        String id = getUniqueID();
        editorCache.put(id, new RepoItem<>(editor));
        return id;
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
