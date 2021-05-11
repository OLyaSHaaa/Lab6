package ticket;

import exceptions.InvalidCoordinatesXException;
import exceptions.InvalidCoordinatesYException;
import exceptions.InvalidFieldException;


public interface CoordinatesBuilder {

    /**
     * Задать координату x
     * @param x координата x
     * @throws InvalidFieldException если значение некорректно
     */
    void setX(Integer x) throws InvalidCoordinatesXException;

    /**
     * Задать координату y
     * @param y координата y
     * @throws InvalidFieldException если значение некорректно
     */
    void setY(double y) throws InvalidCoordinatesYException;

    /**
     * @return объект Coordinates
     */
    Coordinates getCoordinates();
}

