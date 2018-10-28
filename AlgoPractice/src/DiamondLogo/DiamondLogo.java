package DiamondLogo;

public class DiamondLogo {
    public DiamondLogo() {

    }

    String[] logo(int size, char background) {
        int line = (size-1) * 2 + 1;
        String result[] = new String[line];

        int left = line /2;
        int right = line / 2;

        for(int i=0 ; i < line; i++) {
            result[i] = "";

            for(int j = 0 ; j < line; j++) {

                if(j < left || j > right) {
                    result[i] = result[i] + background;
                }
                if(j == left || j == right) {
                    result[i] = result[i] + "X";
                }
                if(j > left && j < right) {
                    result[i] = result[i] + " ";
                }

            }
            if(i < line/2) {
                left = left - 1;
                right = right + 1;
            }
            else {
                left = left + 1;
                right = right - 1;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        new DiamondLogo().logo(2, '.');

    }

}
