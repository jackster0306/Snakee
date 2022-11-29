package Application;

public class Data {
        private String TheNames;
        private String TheScores;

        public String getTheNames() {
            return TheNames;
        }
        public void setTheNames(String name) {
            TheNames = name;
        }

        public String getTheScores() {
            return TheScores;
        }
        public void setTheScores(String score) {
            TheScores = score;
        }
        public Data(String name, String score) {
            TheNames = name;
            TheScores = score;
        }
}