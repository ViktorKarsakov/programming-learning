class Css {
    private String cssLink;

    public Css(String cssLink) {
        this.cssLink = cssLink;
    }

    @Override
    public String toString() {
        return "<link rel=\"stylesheet\" href=\"" + cssLink + "\">";
    }
}
