package com.blogger.words_from_desire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sangupta.blogparser.blogger.BloggerParser;
import com.sangupta.blogparser.domain.Blog;
import com.sangupta.blogparser.domain.BlogPost;

public class Main {
    private static final String FS = File.separator;
    private static final String LOGPATH = Paths.get(System.getProperty("user.dir") + FS + "Properties" + FS + "log4j2.xml").toUri().toString();
    private static final String LOGPARAM = "log4j.configurationFile";
    private static final String BLOGFILE = System.getProperty("user.dir") + FS + "src" + FS + "main" + FS + "resources" + FS + "blog-03-14-2019.xml";
    
    static {
        System.setProperty(LOGPARAM, LOGPATH);
    }

    public static void main(String[] args) throws IOException {
        Logger logger = LogManager.getLogger(Main.class);
        FileReader reader = null;
        try {
            reader = new FileReader(new File(BLOGFILE));
            BloggerParser parser = new BloggerParser();
            Blog blog = parser.parse(reader);
            List<BlogPost> posts = blog.getPosts();
            for (int i = 0; i < posts.size(); i++) {
                logger.info("Title[" + i + "]: " + posts.get(i).getTitle().trim() + ", Published On: " 
                    + posts.get(i).getPublishedOn().toString());
            }
            logger.info("Posts Count: " + posts.size());
        } catch (FileNotFoundException e) {
            logger.error("Main.FileNotFoundException: " + e.getMessage(), e);
        } finally {
            if (reader != null)
                reader.close();
        }
        logger.debug("Done.");
    }

}