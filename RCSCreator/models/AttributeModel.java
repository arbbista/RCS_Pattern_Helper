package models;

public class AttributeModel {
  private String attributePrivacy;
  private String attributeType;
  private String attributeName;
  private String attributeAnnotation;

  //Create only simple attribute
  public AttributeModel(String attributePrivacy, String attributeType, String attributeName) {
    this.attributePrivacy = attributePrivacy;
    this.attributeType = attributeType;
    this.attributeName = attributeName;
  }

  //Create attribute for method parameter. It includes attribute annotation
  public AttributeModel(String attributePrivacy, String attributeAnnotation, String attributeType, String attributeName) {
    this.attributePrivacy = attributePrivacy;
    this.attributeAnnotation = attributeAnnotation;
    this.attributeType = attributeType;
    this.attributeName = attributeName;
  }

  public String getAttributePrivacy() {
    return attributePrivacy;
  }

  public void setAttributePrivacy(String attributePrivacy) {
    this.attributePrivacy = attributePrivacy;
  }

  public String getAttributeType() {
    return attributeType;
  }

  public void setAttributeType(String attributeType) {
    this.attributeType = attributeType;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  public String getAttributeAnnotation() {
    return attributeAnnotation;
  }

  public void setAttributeAnnotation(String attributeAnnotation) {
    this.attributeAnnotation = attributeAnnotation;
  }
}
