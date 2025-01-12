package pl.project.tictactoe.api.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoardConverter implements AttributeConverter<int[][], String> {

    @Override
    public String convertToDatabaseColumn(int[][] board) {
        if (board == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell).append(",");
            }
        }
        return sb.toString();
    }

    @Override
    public int[][] convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return new int[3][3];  // Default empty board if no data is found
        }
        String[] values = dbData.split(",");
        int[][] board = new int[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(values[index++]);
            }
        }
        return board;
    }
}