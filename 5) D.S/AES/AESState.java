public class AESState {
    //Note(sharo): int is 4 bytes, ( byte ) data type is one byte.
    public byte[] bytes = new byte[16];

    public AESState(String input) {
        int k = 0;
        for(int i = 0; i < 16; ++i) {
            //Note(sharo): not a smart way to index multidimentional array,
            //must find but the system has come this way :(
            if(i%4 == 0) k++;
            int index =  (k-1) + 4*( i%4 );
            bytes[index] = (byte)input.charAt(i);
        }
    }

    public AESState() {

    }

    public void printArray() {
        for(int i = 0; i < 16; ++i) {
            System.out.print(Integer.toHexString(((int)(bytes[i]) << 24 >>> 24)) + " ");
            if((i + 1)%4 == 0) System.out.println();
        }
    }

    public byte[] getRow(int rowNumber) {
        byte[] row = new byte[4];
        for(int i = 0; i < 4; ++i) {
            row[i]  = bytes[rowNumber*4 + i];
        }

        return row;
    }

    public byte[] getColumn(int colNumber) {
        byte[] column = new byte[4];
        for(int i = 0; i < 4; ++i) {
            column[i]  = bytes[i*4 + colNumber];
        }

        return column;
    }

    public void setRow(int rowNumber, byte[] row) {
        for(int i = 0; i < row.length; ++i) {
            bytes[rowNumber*4 + i] = row[i];
        }
    }

    public void setColumn(int columnNumber, byte[] column) {
        for(int i = 0; i < column.length; ++i) {
            bytes[i*4 + columnNumber] = column[i];
        }
    }
}
