package models;

import java.util.List;

public class MethodModel {
  private String methodPrivacy;
  private String methodReturnType;
  private String methodName;
  List<AttributeModel> parameters;

  public MethodModel(String methodPrivacy, String methodReturnType, String methodName, List<AttributeModel> parameters) {
    this.methodPrivacy = methodPrivacy;
    this.methodReturnType = methodReturnType;
    this.methodName = methodName;
    this.parameters = parameters;
  }

  public String getMethodPrivacy() {
    return methodPrivacy;
  }

  public void setMethodPrivacy(String methodPrivacy) {
    this.methodPrivacy = methodPrivacy;
  }

  public String getMethodReturnType() {
    return methodReturnType;
  }

  public void setMethodReturnType(String methodReturnType) {
    this.methodReturnType = methodReturnType;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public List<AttributeModel> getParameters() {
    return parameters;
  }

  public void setParameters(List<AttributeModel> parameters) {
    this.parameters = parameters;
  }
}
