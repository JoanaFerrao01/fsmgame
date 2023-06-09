package pt.isec.pa.tinypac.LevelLoader;

import pt.isec.pa.tinypac.model.data.IMazeElement;

public class charToMazeElement {
   public IMazeElement convertToMazeElement(char x){
       switch (x) {
           case 'x':
               IMazeElement newX = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'x';
                   }
               };
               return newX;

           case 'o':
               IMazeElement newO = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'o';
                   }
               };
               return newO;

           case 'y':
               IMazeElement newY = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'y';
                   }
               };
               return newY;

           case 'Y':
               IMazeElement newYY = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'Y';
                   }
               };
               return newYY;

           case 'F':
               IMazeElement newF = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'F';
                   }
               };
               return newF;

           case 'M':
               IMazeElement newM = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'M';
                   }
               };
               return newM;

           case 'W':
               IMazeElement newW = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'W';
                   }
               };
               return newW;

           case 'O':
               IMazeElement newOO = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'O';
                   }
               };
               return newOO;

           case ' ':
               IMazeElement newSpace = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return ' ';
                   }
               };
               return newSpace;
           case 'S':
               IMazeElement superFruit = new IMazeElement() {
                   @Override
                   public char getSymbol() {
                       return 'S';
                   }
               };
               return superFruit;
       }

       return null; //caso nenhum dos simbolos
   }
}

