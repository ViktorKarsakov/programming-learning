class HTML {
    private Head head;
    private Body body;
    private Css css;
    private JS js;

    private HTML(Builder builder) {
        this.head = builder.head;
        this.body = builder.body;
        this.css = builder.css;
        this.js = builder.js;
    }

    @Override
    public String toString() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n");

        if(css != null){
            html.append(css.toString()).append("\n");
        }
        if(head != null){
            html.append(head.toString()).append("\n");
        }
        if(body != null){
            html.append(body.toString()).append("\n");
        }
        if(js != null){
            html.append(js.toString()).append("\n");
        }
        html.append("</html>");

        return html.toString();
    }

    public static class Builder{
        private Head head;
        private Body body;
        private Css css;
        private JS js;

        public Builder setHead(Head head) {
            this.head = head;
            return this;
        }

        public Builder setBody(Body body) {
            this.body = body;
            return this;
        }

        public Builder setCss(Css css) {
            this.css = css;
            return this;
        }

        public Builder setJs(JS js) {
            this.js = js;
            return this;
        }
        public HTML build(){
            return new HTML(this);
        }
    }
}
