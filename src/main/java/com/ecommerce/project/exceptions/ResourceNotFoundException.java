package com.ecommerce.project.exceptions;

/**
 * @author seval
 */
public class ResourceNotFoundException extends Throwable{
    String resourceName;
    String field;
    String fieldName;
    String fieldId;

    public ResourceNotFoundException(String message, Throwable cause, String resourceName, String field, String fieldName) {
        super(message, cause);
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
}
