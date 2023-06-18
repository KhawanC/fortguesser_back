package br.com.egypto.plataformasocial.client.fortnite.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Root {
    private int status;
    private Data data;

    @Builder
    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Data{
        private String id;
        private String name;
        private String description;
        private Type type;
        private Rarity rarity;
        private Object series;
        private Set set;
        private Introduction introduction;
        private Images images;
        private ArrayList<Variant> variants;
        private Object searchTags;
        private ArrayList<String> gameplayTags;
        private Object metaTags;
        private Object showcaseVideo;
        private Object dynamicPakId;
        private Object displayAssetPath;
        private Object definitionPath;
        private String path;
        private Date added;
        private Object shopHistory;

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Images{
            private String smallIcon;
            private String icon;
            private Object featured;
            private Object other;
        }

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Introduction{
            private String chapter;
            private String season;
            private String text;
            private int backendValue;
        }

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Option{
            private String tag;
            private String name;
            private String image;
        }

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Rarity{
            private String value;
            private String displayValue;
            private String backendValue;
        }

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Set{
            private String value;
            private String text;
            private String backendValue;
        }

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Type{
            private String value;
            private String displayValue;
            private String backendValue;
        }

        @Builder
        @Getter
        @Setter
        @ToString
        @AllArgsConstructor
        @NoArgsConstructor
        private static class Variant{
            private String channel;
            private String type;
            private ArrayList<Option> options;
        }
    }
}
