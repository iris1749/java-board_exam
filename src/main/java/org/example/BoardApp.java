package org.example;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BoardApp {
    ArrayList<Article> articleList = new ArrayList<>();

    public void run() {
        Scanner scan = new Scanner(System.in);

        int articleId = 4;

        Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 0, getCurrentDateTime());
        Article a2 = new Article(2, "자바 질문좀 할게요~", "냉무", 0, getCurrentDateTime());
        Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 0, getCurrentDateTime());

        articleList.add(a1);
        articleList.add(a2);
        articleList.add(a3);

        while (true) {

            System.out.print("명령어 : ");
            String cmd = scan.nextLine();

            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break; // 반복문 탈출

            } else if (cmd.equals("add")) {

                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = scan.nextLine();

                System.out.print("게시물 내용을 입력해주세요 : ");
                String detail = scan.nextLine();

                Article article = new Article(articleId, title, detail, 0, getCurrentDateTime());

                articleList.add(article);

                System.out.println("게시물이 등록되었습니다.");

                articleId++;

            } else if (cmd.equals("list")) {
                System.out.println("===================");
                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);

                    System.out.println("번호 : " + article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }

            } else if (cmd.equals("update")) {
                System.out.print("수정할 게시물 번호를 입력해주세요 : ");
                int id = Integer.parseInt(scan.nextLine());

                int index = findIndexById(id);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                System.out.print("새로운 제목을 입력해주세요 : ");
                String newTitle = scan.nextLine();

                System.out.print("새로운 내용을 입력해주세요 : ");
                String newDetail = scan.nextLine();

                Article target = articleList.get(index);
                target.setTitle(newTitle);
                target.setDetail(newDetail);
                target.setHit(0);

                System.out.printf("%d번 게시물이 수정되었습니다.\n", id);

            } else if (cmd.equals("delete")) {
                System.out.print("삭제할 게시물 번호를 입력해주세요 : ");
                int id = Integer.parseInt(scan.nextLine());

                int index = findIndexById(id);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                articleList.remove(index);
                System.out.printf("%d번 게시물이 삭제되었습니다.\n", id);

                for (int i = index; i < articleList.size(); i++) {
                    Article article = articleList.get(i);
                    article.setId(article.getId() - 1);
                }

            } else if (cmd.equals("detail")) {
                System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
                int id = Integer.parseInt(scan.nextLine());

                int index = findIndexById(id);

                if (index == -1) {
                    System.out.println("없는 게시물입니다.");
                    continue;
                }

                Article article = articleList.get(index);

                article.increaseHit();

                System.out.println("===================");
                System.out.println("번호 : " + article.getId());
                System.out.println("제목 : " + article.getTitle());
                System.out.println("내용 : " + article.getDetail());
                System.out.println("등록날짜 : " + article.getRegDate());
                System.out.println("조회수 : " + article.getHit());
                System.out.println("===================");

            } else if (cmd.equals("search")) {
                System.out.print("검색 키워드를 입력해 주세요 : ");
                String keyword = scan.nextLine();
                System.out.println("===================");

                for (int i = 0; i < articleList.size(); i++) {
                    Article article = articleList.get(i);

                    if (article.getTitle().contains(keyword)) {
                        System.out.println("번호 : " + article.getId());
                        System.out.println("제목 : " + article.getTitle());
                        System.out.println("===================");
                    }

                }

            }
        }
    }

    public int findIndexById(int id) {
        int index = -1;

        for (int i = 0; i < articleList.size(); i++) {
            Article article = articleList.get(i);

            if (article.getId() == id) {
                index = i;
            }
        }
        return index;
    }

    public String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();

        // 날짜와 시간의 형식을 지정합니다. 여기서는 연-월-일 시:분:초 형식을 사용합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        // 지정한 형식으로 날짜와 시간을 출력합니다.
        String formattedDate = now.format(formatter);

        return formattedDate;
    }
}