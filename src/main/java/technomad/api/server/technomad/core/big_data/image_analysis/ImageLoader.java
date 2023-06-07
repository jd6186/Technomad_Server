package technomad.api.server.technomad.core.big_data.image_analysis;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ImageLoader {
    private final ResourceLoader resourceLoader;

    public ImageLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public File[] getImageFiles(String path) throws IOException {
        Resource resource = resourceLoader.getResource(path); // "classpath:static/"
        String basePath = resource.getURI().getPath();

        return new File(basePath).listFiles();
    }
}