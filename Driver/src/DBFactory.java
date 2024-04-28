class DBFactory {
    public AbstractDB createHandler(String handlerType) {
        if (handlerType.equalsIgnoreCase("Database")) {
            return (AbstractDB)  new Database();
        } else if (handlerType.equalsIgnoreCase("FileHandler")) {
            return (AbstractDB)  new FileHandler();
        } else {
            throw new IllegalArgumentException("Unknown handler type: " + handlerType);
        }
    }
}