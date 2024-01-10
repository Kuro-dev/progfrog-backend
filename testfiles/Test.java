public void main() {

    for (int i = 0; i <frog.getClass().getDeclaredFields().length ; i++) {
        System.out.println(frog.getClass().getDeclaredFields()[i].getName());
    }

    public void move(int steps){
        for (int i = 0; i < steps; i++) {
            move();
        }
    }
}
