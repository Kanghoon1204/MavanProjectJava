package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class WordCRUD implements ICRUD{
    ArrayList <Word> list;
    Scanner s;
    final String fname = "Dictionary.txt";
    WordCRUD(Scanner s)
    {
        list = new ArrayList<>();
        this.s = s;
    }
    @Override
    public Object add() {
        System.out.println("=> 난이도(1,2,3) &7 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();

        System.out.println("뜻 입력: ");
        String meaning = s.nextLine();

        return new Word(0, level ,word ,meaning);
    }
    public void addItem()
    {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새 단어가 단어장에 추가되었습니다.");
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
    public void listAll()
    {
        System.out.println("-----------------------------");
        for (int i = 0; i <list.size(); i++)
        {
            System.out.print((i+1) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("-----------------------------");
    }

    public ArrayList<Integer> listAll(String keyword)
    {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;
        System.out.println("-----------------------------");
        for (int i = 0; i <list.size(); i++)
        {
            String word = list.get(i).getWord();
            if (!word.contains(keyword)) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("-----------------------------");
        return idlist;
    }

    public void updateItem() {
        System.out.println("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine(); // 숫자를 받고 뒤에 공백(엔터)로 넘어가는것을 막기위해.
        System.out.print("=> 뜻 입력 : ");
        if (idlist.size() >= id)
        {
            String meaning = s.nextLine();
            Word word = list.get(idlist.get(id-1));
            word.setMeaning(meaning);
            System.out.println("단어가 수정되었습니다.");
        }
        else
        {
            System.out.println("수정 될 단어가 없습니다.");
        }
    }

    public void deleteItem() {
        System.out.println("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();

        System.out.print("=> 정말로 삭제하실래요? (Y/N) ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("y"))
        {
            list.remove((int)idlist.get(id-1));// (int)를 붙혀줘야 정상적으로 작동한다.
            System.out.println("단어가 삭제되었습니다. ");
        }
        else
            System.out.println("취소되었습니다.");
    }
    public void loadFile() {
        try {
            BufferedReader br= new BufferedReader(new FileReader(fname));
            String line;
            int count = 0;

            while(true)
            {
                line = br.readLine();
                if(line == null) break;

                String data[] = line.split("\\|"); // '|'를 이용해서 나누겠다.
                int level = Integer.parseInt(data[0]); //문자열을 숫자로 바꿔주는
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning));
                count++;
            }
            br.close();
            System.out.println("==>" + count + "개 로딩 완료!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void saveFile() {
        try {
            PrintWriter pr = new PrintWriter(new FileWriter(fname));
            for (Word one : list)
            {
                pr.write(one.toFileStirng() + "\n");
            }
            //format 레벨|단어|뜻
            pr.close();
            System.out.println ("==> 데이터 저장 완료 !!!");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchLevel() {
        System.out.println("=> 원하는 레벨은? (1:초급 2:중급 3:고급 ");
        int level = s.nextInt();
        s.nextLine();
        int count = 0;
        System.out.println("-----------------------------");
        for (int i = 0; i <list.size(); i++)
        {
            if(level == list.get(i).getLevel())
            {
                count++;
                System.out.print((count) + " ");
                System.out.println(list.get(i).toString());
            }
        }
        System.out.println("-----------------------------");
    }

    public void searchWord() {
        System.out.println("=> 원하는 단어는? : ");
        String keyword = s.next();
        listAll(keyword);

    }
}
