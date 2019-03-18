package com.blogger.words_from_desire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.sangupta.blogparser.blogger.BloggerParser;
import com.sangupta.blogparser.domain.Blog;
import com.sangupta.blogparser.domain.BlogPost;

public class Main {
    private static final String FS = File.separator;
    private static final String BLOGFILE = System.getProperty("user.dir") + FS + "src" + FS 
        + "main" + FS + "resources" + FS + "blog-03-14-2019.xml";

    public static void main(String[] args) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader(new File(BLOGFILE));
            BloggerParser parser = new BloggerParser();
            Blog blog = parser.parse(reader);
            List<BlogPost> posts = blog.getPosts();
            for (int i = 0; i < posts.size(); i++) {
                System.out.println("Title[" + i + "]: " + posts.get(i).getTitle() + ", Published On: " 
                    + posts.get(i).getPublishedOn().toString());
            }
            System.out.println("Posts Count: " + posts.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}