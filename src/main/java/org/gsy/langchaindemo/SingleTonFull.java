package org.gsy.langchaindemo;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-29 08:46
 **/
public class SingleTonFull {

    private static final SingleTonFull INSTANCE = new SingleTonFull();

    private  SingleTonFull(){}

    public static SingleTonFull getInstance(){
        return INSTANCE;
    }

}
