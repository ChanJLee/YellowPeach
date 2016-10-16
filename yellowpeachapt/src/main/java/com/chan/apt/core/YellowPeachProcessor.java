package com.chan.apt.core;

import com.chan.apt.annotations.JoinView;
import com.chan.apt.checker.AnnotationChecker;
import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@AutoService(Processor.class)
public class YellowPeachProcessor extends AbstractProcessor {
    private Filer mFiler;
    private Messager mMessager;
    private AnnotationChecker mAnnotationChecker;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();

        mAnnotationChecker = new AnnotationChecker(mMessager);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> set = roundEnv.getElementsAnnotatedWith(JoinView.class);
        if (set != null) {

            CodeGenerator codeGenerator = new CodeGenerator(mFiler, mMessager);
            for (Element element : set) {
                if (!mAnnotationChecker.checkAnnotation(element)) {
                    return false;
                }
                codeGenerator.add((VariableElement) element);
            }
            codeGenerator.generate();
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> set = new HashSet<>();
        set.add(JoinView.class.getCanonicalName());
        return set;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
