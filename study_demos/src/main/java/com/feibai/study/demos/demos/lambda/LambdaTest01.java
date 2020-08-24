package com.feibai.study.demos.demos.lambda;

/**
 * lambda没有参数、没有返回值
 *
 * @author feibai
 */
public class LambdaTest01 {
  // 静态内部类
  static class LikeInnerStatic implements ILike {
    @Override
    public void function_lambda() {
      System.out.println("i like lambda2 ");
    }
  }

  public static void main(String[] args) {
    ILike like = new LikeOutter();
    like.function_lambda();

    like = new LikeInnerStatic();
    like.function_lambda();

    // 局部内部类--定义在方法内的类称为局部内部类
    class LikeFunctionLocal implements ILike {
      @Override
      public void function_lambda() {
        System.out.println("i like lambda3 ");
      }
    }

    like = new LikeFunctionLocal();
    like.function_lambda();

    // 匿名内部类
    like = new ILike() {
      @Override
      public void function_lambda() {
        System.out.println("i like lambda4 ");
      }
    };
    like.function_lambda();

    // lambda
    like = () -> {
      System.out.println("i like lambda5 ");
    };
    like.function_lambda();

    /*
     * lambda推导必须存在类型 ()-> { System.out.println("i like lambda5 "); }.lambda();
     */
  }
}

interface ILike {
  void function_lambda();
}

//外部类
class LikeOutter implements ILike {

  @Override
  public void function_lambda() {
    System.out.println("i like lambda ");
  }

}