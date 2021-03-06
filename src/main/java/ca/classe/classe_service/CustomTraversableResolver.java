package ca.classe.classe_service;

import java.lang.annotation.ElementType;

import javax.validation.Path;
import javax.validation.Path.Node;
import javax.validation.TraversableResolver;

public class CustomTraversableResolver implements TraversableResolver {

    @Override
    public boolean isReachable(Object traversableObject, Node traversableProperty, Class<?> rootBeanType,
            Path pathToTraversableObject, ElementType elementType) {
        return true;
    }

    @Override
    public boolean isCascadable(Object traversableObject, Node traversableProperty, Class<?> rootBeanType,
            Path pathToTraversableObject, ElementType elementType) {
        return true;
    }
}
