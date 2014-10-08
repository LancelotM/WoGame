package com.unicom.game.center.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrivilegeVo {

        @JsonProperty("id")
        private int id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("name")
        private String name;

        @JsonProperty("url")
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {

            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {

            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
}
