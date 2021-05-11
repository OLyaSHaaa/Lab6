package ticket;


public interface CoordinatesValidator {

    /**
     * @param x координата x
     * @return true - корректно, false - некорректно
     */
    boolean validateCoordinatesX(Integer x);

    /**
     * @param y координата y
     * @return true - корректно, false - некорректно
     */
    boolean validateCoordinatesY(double y);
}
