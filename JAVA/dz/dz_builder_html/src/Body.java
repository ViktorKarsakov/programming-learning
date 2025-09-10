class Body {
    private String content;

    public Body(String content){
        this.content = content;
    }

    @Override
    public String toString() {
        return "<body>" + content + "</body>";
    }
}
