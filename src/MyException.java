public class MyException extends Exception{
    @Override
    public String getMessage() {
        return "Неправильно введена дата (дата должна быть в промежутке от 2002 до 2021 включительно)";
    }

}
