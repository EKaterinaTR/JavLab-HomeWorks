package ru.itis.javalab.utils.formcreaters;

import com.google.auto.service.AutoService;
import ru.itis.javalab.utils.formcreaters.annotations.HtmlForm;
import ru.itis.javalab.utils.formcreaters.annotations.HtmlInput;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;


@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"ru.itis.javalab.utils.formcreaters.annotations.HtmlForm",
        "ru.itis.javalab.utils.formcreaters.annotations.HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // получить типы с аннотаций ru.itis.javalab.utils.formcreaters.annotations.HtmlForm
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        Set<? extends Element> annotatedInputElements = roundEnv.getElementsAnnotatedWith(HtmlInput.class);

        for (Element element : annotatedElements) {
            // получаем полный путь для генерации html
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            // ru.itis.javalab.models.User.class -> ru.itis.javalab.models.User.html
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile()));
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                writer.write("<form action='" + annotation.action() + "' method='" + annotation.method() + "'/>");
                writer.newLine();
                element.getAnnotation(HtmlInput.class);
                for (Element el: annotatedInputElements) {
                    if(el.getEnclosingElement().equals(element)) {
                        HtmlInput inputAnnotation = el.getAnnotation(HtmlInput.class);
                        //type="text" name="nickname" placeholder="Ваш ник">
                        writer.write("<input type= \"" + inputAnnotation.type() + "\" name = \""+
                                inputAnnotation.name()+ "\" placeholder=\""+inputAnnotation.placeholder() +"\" >");
                    }

                }

                writer.newLine();
                writer.write("</form>");
                writer.close();
            } catch (IOException e) {

                throw new IllegalStateException(e);
            }

        }
        return true;
    }
}
