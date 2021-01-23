package ru.itis.javalab.utils.formcreaters;

import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.javalab.utils.formcreaters.annotations.HtmlForm;
import ru.itis.javalab.utils.formcreaters.annotations.HtmlInput;
import ru.itis.javalab.utils.formcreaters.model.Form;
import ru.itis.javalab.utils.formcreaters.model.Input;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.javalab.utils.formcreaters.annotations.HtmlForm",
        "ru.itis.javalab.utils.formcreaters.annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотаций ru.itis.javalab.utils.formcreaters.annotations.HtmlForm

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        try {
            configuration.setTemplateLoader(new FileTemplateLoader(new File("src/main/resources/ftlh")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element element : annotatedElements) {

            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();

            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                Template template = configuration.getTemplate("form.ftlh");
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                Form form = new Form(annotation.action(), annotation.method());

                List inputs = new LinkedList();
                List<? extends Element> elements = element.getEnclosedElements();


                for (Element el : elements) {
                    HtmlInput inputAnnotation = el.getAnnotation(HtmlInput.class);
                    if (inputAnnotation != null) {
                        inputs.add(new Input(inputAnnotation.type(), inputAnnotation.name(), inputAnnotation.placeholder()));
                    }
                }

                Map<String, Object> attributes = new HashMap<>();
                attributes.put("form", form);
                attributes.put("inputs", inputs);
                FileWriter fileWriter = new FileWriter(out.toFile());
                template.process(attributes, fileWriter);

            } catch (Exception e) {

                throw new IllegalStateException(e);
            }
//
        }
        return true;
    }
//


}
