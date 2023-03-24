package com.library.management.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	String resourceName;
	String fieldName;
	long field;

	public ResourceNotFoundException(String resourceName, String fieldName, long field) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, field));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.field = field;
	}

	public String getResourceName() {
		return resourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public long getField() {
		return field;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setField(long field) {
		this.field = field;
	}

}
