package Ceaser;

public class Ceaser {

    public  Ceaser() {
        System.out.println(new Solution().solution("AB Z", 1));
    }

    class Solution {

        public String solution(String s, int n) {
            String answer = "";

            for(int i=0 ; i < s.length(); i++) {
                char temp = s.charAt(i);

                if(temp != ' ') {



                    int tempint = temp;

                    if(tempint < 'a') {
                        int changeint = temp + n;

                        if((changeint - 'A') > 25) {
                            changeint = changeint - 26;
                        }
                        char chagechar = (char)changeint;
                        answer = answer + chagechar;

                    }
                    else {
                        int changeint = temp + n;

                        if((changeint - 'a') > 25) {
                            changeint = changeint - 26;
                        }
                        char chagechar = (char)changeint;
                        answer = answer + chagechar;
                    }
                }
                else {
                    answer = answer + ' ';
                }



            }

            return answer;
        }
    }

    public static void main(String args[]) {
        new Ceaser();

    }
}
