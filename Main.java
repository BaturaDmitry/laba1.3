package com.company;


import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String num;

        label:while (true) {
            System.out.println("Введите 1, если хотите проверить эквивалентность двух многочленов");
            System.out.println("Введите 2, если хотите найти конкретное значение многочлена в целочисленной точке");
            System.out.println("Введите 3, если хотите сложить два многочлена");
            do {
                while (!in.hasNextInt()) {
                    System.out.println("Некорректный ввод. Повторите попытку");
                    in.next();
                }

                num = in.next();
                if (Integer.parseInt(num) < 1 || Integer.parseInt(num) > 3)
                    System.out.println("Некорректный ввод, попробуйте еще раз");
            } while (Integer.parseInt(num) < 1 || Integer.parseInt(num) > 3);
            if (Integer.parseInt(num) == 1) {

                System.out.println("Введите степень первого многочлена или введите back, чтобы вернуться назад");

                while (!in.hasNextInt()) {
                    if(in.next().equals("back")){
                        continue label;
                    }
                    System.out.println("Некорректный ввод. Повторите попытку");
                }
                List l1 = vvod(in);

                System.out.println("Введите степень второго многочлена");
                while (!in.hasNextInt()) {
                    System.out.println("Некорректный ввод. Повторите попытку");
                    in.next();
                }

                List l2 = vvod(in);
                System.out.println(Equality(l1, l2));
                break;
            }
            if (Integer.parseInt(num) == 2) {

                System.out.println("Введите степень многочлена или введите back, чтобы вернуться назад");

                while (!in.hasNextInt()) {
                    if(in.next().equals("back")){
                        continue label;
                    }
                    System.out.println("Некорректный ввод. Повторите попытку");
                }

                List l3 = vvod(in);
                System.out.println("Введите x");
                while (!in.hasNextInt()) {
                    System.out.println("Некорректный ввод. Повторите попытку");
                    in.next();
                }

                num = in.next();
                System.out.println(Meaning(Integer.parseInt(num), l3));
                l3.printPol();
                break;
            }

            if (Integer.parseInt(num) == 3) {
                System.out.println("Введите степень первого многочлена или введите back, чтобы вернуться назад");
                while (!in.hasNextInt()) {
                    if(in.next().equals("back")){
                        continue label;
                    }
                    System.out.println("Некорректный ввод. Повторите попытку");
                }

                List l4 = vvod(in);
                System.out.println("Введите степень второго многочлена");
                while (!in.hasNextInt()) {
                    System.out.println("Некорректный ввод. Повторите попытку");
                    in.next();
                }
                List l5 = vvod(in);
                List p = new List();
                add(p, l4, l5);
                p.printPol();
                break;
            }
        }
    }

    static int Meaning(int x, List list) {
        int sum = 0;
        for (int i = 0; i < list.size; i++) {
            sum += list.get(i).a * Math.pow(x, list.get(i).n);
        }
        return sum;

    }

    static List vvod(Scanner in) {
        int b;


        b = in.nextInt();

        int a;
        List list = new List();
        for (int i = b; i >= 0; i--) {
            System.out.print("Введите коэффициент a для многочлена х^" + i + "\t");

            while (!in.hasNextInt()) {
                System.out.println("Некорректный ввод. Повторите попытку");
                in.next();
            }
            a = in.nextInt();
            if (a != 0)
                list.add(i, a);
        }

        return list;
    }

    static boolean Equality(List p, List q) {
        if(p.size==q.size) {
            for (int i = 0; i < Math.max(p.size, q.size);i++) {
                if (p.get(i).a==q.get(i).a&&p.get(i).n==q.get(i).n){
                    return true;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    static void add(List p, List q, List r) {

        int i = 0;
        int j = 0;


        do {
            if (i != q.size || j != r.size) {
                if (q.get(i).n > r.get(j).n) {
                    p.add(q.get(i).n, q.get(i).a);
                    i++;
                } else if (q.get(i).n < r.get(j).n) {
                    p.add(r.get(j).n, r.get(j).a);
                    j++;
                } else if (q.get(i).n == r.get(j).n) {
                    int A = q.get(i).a + r.get(j).a;
                    p.add(q.get(i).n, A);
                    i++;
                    j++;
                }
            }
            if (i == q.size && j != r.size) {
                while (j != r.size) {
                    p.add(r.get(j).n, r.get(j).a);
                    j++;
                }


            }
            if (j == r.size && i != q.size) {

                while (i != q.size) {
                    p.add(q.get(i).n, q.get(i).a);
                    i++;
                }
            }
        } while ((i != q.size) || (j != r.size));
    }
}


class Link {
    public Link next;
    int n;
    int a;

    Link(int n, int a) {
        this.n = n;
        this.a = a;

    }
}

class List {
    Link first;
    int size;

    List() {

    }

    public void add(int n, int a) {
        if (first == null) {
            first = new Link(n, a);
            size++;
        } else {
            Link current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Link(n, a);
            size++;
        }


    }

    public Link get(int index) {
        Link current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public void printPol() {
        Link current = first;
        for (int i = 0; i < size; i++) {
            if (current.next != null) {
                System.out.printf("%dx^%d+", current.a, current.n);
                current = current.next;
            } else {
                System.out.printf("%dx^%d", current.a, current.n);
                break;
            }
        }

    }
}