class UIFactory {
    public AbstractUI createUI(String uiType) {
        if (uiType.equalsIgnoreCase("Java")) {
            return (AbstractUI) new JavaUI();
        } else if (uiType.equalsIgnoreCase("Terminal")) {
            return (AbstractUI) new TerminalUI();
        } else {
            throw new IllegalArgumentException("Unknown UI type: " + uiType);
        }
    }
}