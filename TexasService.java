package garbage.provider.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author luoxin on on 2019/7/3
 */
@Slf4j
@Service
public class TexasService {

    public static List<String> initTexas() {
        List<String> poker = new ArrayList<>();
        List<String> color = new ArrayList<>();
        color.add("♦");
        color.add("♥");
        color.add("♠");
        color.add("♣");
        //初始化2-10
        int i = 2;
        int j = 0;
        while (i < 11 && j < color.size()) {
            poker.add(color.get(j) + i);
            i++;
            if (i == 11) {
                i = 2;
                j++;
            }
        }
        //初始化J-A
        int k = 0;
        while (k < color.size()) {
            poker.add(color.get(k) + "J");
            poker.add(color.get(k) + "Q");
            poker.add(color.get(k) + "K");
            poker.add(color.get(k) + "A");
            k++;
        }
        return poker;
    }


    public static List<Integer> dealTexas(int player) {
        List<String> pokers = initTexas();
        List<Integer> allNumbers = new ArrayList<>();
        Random random = new Random();
        for (int p = 1; p < player+1; p++) {
            int i = 0;
            List<Integer> theirNumbers = new ArrayList<>();
            while (i < 2) {
                int number = random.nextInt(pokers.size());
                while (allNumbers.contains(number)) {
                    number = random.nextInt(pokers.size());
                }
                allNumbers.add(number);
                theirNumbers.add(number);
                i++;
            }
            System.out.println(p + "号玩家的底牌: " + pokers.get(theirNumbers.get(0)) + "、" + pokers.get(theirNumbers.get(1)));
        }
        return allNumbers;
    }

    public static void dealThree(List<Integer> allNumbers) {
        List<String> pokers = initTexas();
        Random random = new Random();
        int i = 0;
        while (i < 5) {
            int number = random.nextInt(pokers.size());
            while (allNumbers.contains(number)) {
                number = random.nextInt(pokers.size());
            }
            allNumbers.add(number);
            i++;
            System.out.println("第" + i + "张底牌: " + pokers.get(number));
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入玩家数量");
        int number = in.nextInt();
        try {
            dealThree(dealTexas(number));
        } catch (Exception e) {
            System.out.println("输入有误，请检查后重新开始");
        }
    }


}
