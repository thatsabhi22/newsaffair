package com.theleafapps.pro.newsaffair.models;

import java.util.List;

/**
 * Created by aviator on 30/12/16.
 */

public class Article {

    /**
     * status : ok
     * source : the-next-web
     * sortBy : latest
     * articles : [{"author":"Matthew Hughes","title":"4 reasons why Pokemon Go was the standout mobile game of 2016","description":"In 2010, it was Angry Birds. In 2014, it was Candy Crush Saga. And in 2016, Pokémon Go was the standout mobile game. The game first launched on July 6 in Australia, New Zealand, and the United States. It didn\u2019t take long for the game\u2019s servers to crumble under the strain of millions of nostalgic millennials, \u2026","url":"http://thenextweb.com/apps/2016/12/30/4-reasons-pokemon-go-standout-mobile-game-2016/","urlToImage":"https://cdn3.tnwcdn.com/wp-content/blogs.dir/1/files/2016/09/pokemon-go-google-maps-timeline-activity-img.jpg","publishedAt":"2016-12-30T12:42:22Z"},{"author":null,"title":"Offers | The Next Web","description":null,"url":"http://thenextweb.com/offers/","urlToImage":"http://cdn1.tnwcdn.com/wp-content/themes/cyberdelia/assets/img/logo-tnw-red.svg","publishedAt":null},{"author":"Matthew Hughes","title":"Why Microsoft isn't the smartphone leader it should be","description":"Is time running out for Microsoft\u2019s mobile ambitions?","url":"http://thenextweb.com/microsoft/2016/12/30/why-isnt-microsoft-the-smartphone-leader-it-should-be/","urlToImage":"https://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2016/03/Windows10Phone.jpg","publishedAt":"2016-12-30T09:02:03Z"},{"author":"Bryan Clark","title":"These are the tech stories we're most excited to follow in 2017","description":"Let's take a second to appreciate where we are, and what's on the horizon for tech in 2017.","url":"http://thenextweb.com/opinion/2016/12/30/these-are-the-tech-stories-were-most-excited-to-follow-in-2017/","urlToImage":"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2016/12/tech_excited_2017.png","publishedAt":"2016-12-30T06:14:40Z"},{"author":"Mix","title":"10 of the most captivating videos that broke the internet in 2016","description":"From politics to advertising, video played an integral part in internet culture over the last twelve months. Which ones will end up in our annual roundup?","url":"http://thenextweb.com/video/2016/12/29/10-of-the-most-captivating-videos-that-broke-the-internet-in-2016/","urlToImage":"https://cdn0.tnwcdn.com/wp-content/blogs.dir/1/files/2016/12/casey.gif","publishedAt":"2016-12-30T06:11:01Z"},{"author":"Josh Levenson","title":"Best Xbox One games of 2016","description":"2016 has seen the release of some great Xbox One games, and now that the year\u2019s drawing to a close, we\u2019ve decided to round up a handful of the best ones to add to your wishlist this holiday season. Battlefield 1 Europe, are you ready? TNW Conference is back for its 12th year. Explore our \u2026","url":"http://thenextweb.com/gaming/2016/12/29/best-xbox-one-games-2016/","urlToImage":"https://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2016/12/shutterstock_475444855.jpg","publishedAt":"2016-12-29T22:12:17Z"},{"author":"TNW Deals","title":"Find new customers and amp up your growth with this Marketing Analytics Mastery training","description":"Engaging potential customers online isn\u2019t just about marketing\u2026 it\u2019s a science. Loads of data and analytics tools are available to help you effectively reach potential customers, and this Marketing Analytics Mastery course bundle is key to deciphering it all. Earn your online marketing stripes for over 90 percent from TNW Deals. This bundle covers 62 \u2026","url":"http://thenextweb.com/offers/2016/12/29/find-new-customers-and-amp-up-your-growth-with-this-marketing-analytics-mastery-training/","urlToImage":"https://cdn2.tnwcdn.com/wp-content/blogs.dir/1/files/2016/12/29KE4tg.jpg","publishedAt":"2016-12-29T09:26:22Z"},{"author":"Abhimanyu Ghoshal","title":"10 of the best Windows 10 apps for 2016","description":"We've rounded up 10 cool new and updated Windows 10 apps of 2016, for things like streaming media, taking notes, working on design projects and more.","url":"http://thenextweb.com/apps/2016/12/29/10-best-windows-10-apps-2016/","urlToImage":"https://cdn2.tnwcdn.com/wp-content/blogs.dir/1/files/2016/12/Windows-apps-header.jpg","publishedAt":"2016-12-29T15:41:31Z"},{"author":"TNW Deals","title":"Turn into a cyber security pro with this White Hat Hacker training... for any price you\u2019d like","description":"White hat hackers are the superheroes of the web, protecting unsuspecting businesses and individuals from digital criminals bent on cyber-mayhem. Learn what it takes to safeguard your company or personal data with this White Hat Hacker 2016 bundle of courses. Best of all, you can get all this knowledge at any price you want to \u2026","url":"http://thenextweb.com/offers/2016/12/29/turn-into-a-cyber-security-pro-with-this-white-hat-hacker-training-for-any-price-youd-like/","urlToImage":"https://cdn2.tnwcdn.com/wp-content/blogs.dir/1/files/2016/12/MoOfxCQ.jpg","publishedAt":"2016-12-29T09:26:48Z"},{"author":"Alexander Maasik","title":"How to work less, get better results","description":"Imagine having a couple extra hours of free time a day. Wouldn't it be nice? Here's how to make the most of your work day to get more shit done in less time","url":"http://thenextweb.com/insights/2016/12/29/how-to-work-less-get-better-results/","urlToImage":"https://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2016/10/shutterstock_100316117.jpg","publishedAt":"2016-12-29T09:33:16Z"}]
     */

    private String status;
    private String source;
    private String sortBy;
    private List<ArticlesBean> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {
        /**
         * author : Matthew Hughes
         * title : 4 reasons why Pokemon Go was the standout mobile game of 2016
         * description : In 2010, it was Angry Birds. In 2014, it was Candy Crush Saga. And in 2016, Pokémon Go was the standout mobile game. The game first launched on July 6 in Australia, New Zealand, and the United States. It didn’t take long for the game’s servers to crumble under the strain of millions of nostalgic millennials, …
         * url : http://thenextweb.com/apps/2016/12/30/4-reasons-pokemon-go-standout-mobile-game-2016/
         * urlToImage : https://cdn3.tnwcdn.com/wp-content/blogs.dir/1/files/2016/09/pokemon-go-google-maps-timeline-activity-img.jpg
         * publishedAt : 2016-12-30T12:42:22Z
         */

        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }
    }
}
