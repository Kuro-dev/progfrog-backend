public void main() {

    for (int i = 0; i <frog.getClass().getDeclaredFields().length ; i++) {
        System.out.println(frog.getClass().getDeclaredFields()[i].getName());
    }
}
