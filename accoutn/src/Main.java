import ru.t1.app.Account;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Account acc = new Account("test1");
        acc.setNameClient("test2");
        acc.setNameClient("test3");
        acc.setNameClient("test4");
        System.out.println(acc.getHistory());

        acc = acc.undo();
        System.out.println("undo1="+acc+">>>");
        System.out.println(acc.getHistory());


        acc = acc.undo();
        System.out.println("undo2="+acc+">>>");
        System.out.println(acc.getHistory());

        acc = acc.undo();
        System.out.println("undo3="+acc+">>>");
        System.out.println(acc.getHistory());

        acc = acc.undo();
        System.out.println("undo4="+acc+">>>");
        System.out.println(acc.getHistory());



    }
}