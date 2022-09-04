/*class 와 interface를 많이 활용
* C R U D 구현하기 interface는 확장가능성을 생각해서 만든 것.
* 인터페이스 -> ICRUD*
* 클래스 -> Word WordCRUD(인터페이스에서 상속) WordManager
* 출력 -> [난이도|단어|단어의 뜻]*/
package org.example;
public class Main {
    public static void main(String[] args) {
        new WordManager().selectMenu();
    }
}
