package kakao;

import java.util.*;

public class Pms_브라이언의고민 {

        public String solution(String sentence) {
            StringBuilder answerList = new StringBuilder();

            LinkedHashMap<Character, ArrayList<Integer>> lowerCount = new LinkedHashMap<>();
            String invalid = "invalid";

            try {
                int size = sentence.length();

                for(int i=0; i<size; i++){
                    char c = sentence.charAt(i);

                    if(Character.isLowerCase(c)){
                        if(!lowerCount.containsKey(c)){
                            lowerCount.put(c, new ArrayList<Integer>());
                        }
                        lowerCount.get(c).add(i);
                    }
                }

                int stringIdx = 0;
                int startChar, endChar;
                int lastStartChar = -1, lastEndChar = -1;
                int startWord = 0, endWord = 0;
                int lastStartWord= -1, lastEndWord = -1;
                int count, distance;
                int rule = 0;

                ArrayList<Integer> temp;
                for(char c : lowerCount.keySet()){
                    temp = lowerCount.get(c);
                    count = temp.size();
                    startChar = temp.get(0);
                    endChar = temp.get(count-1);


                    if(count == 1 || count >= 3){
                        for(int i=1; i<count; i++){
                            //간격 2 넘어가면 invalid
                            if(temp.get(i) - temp.get(i-1) != 2) return invalid;
                        }
                        rule = 1;
                    }

                    else if (count == 2){
                        distance = endChar - startChar;

                        //규칙 2  확인
                        if(distance == 2 && (endChar < lastEndChar && startChar > lastStartChar)){
                            rule = 1;
                        }
                        else if(distance >= 2){
                            rule = 2;
                        }

                        //소문자 연속 확인
                        else  return invalid;
                    }

                    if(rule == 1){
                        startWord = startChar -1;
                        endWord = endChar+1;

                        //이전 단어 안에 포함되면
                        if(lastStartWord < startWord && lastEndWord > endWord){

                            if(startChar - lastStartChar  == 2 && lastEndChar - endChar == 2){
                                continue;
                            }
                            else return invalid;
                        }
                    }

                    else if (rule == 2){
                        startWord = startChar;
                        endWord = endChar;

                        //규칙 2는 중복 확인
                        if(lastStartWord < startWord && lastEndWord > endWord) return invalid;
                    }

                    if(lastEndWord >= startWord) return  invalid;

                    if(stringIdx < startWord){
                        answerList.append(makeWord(sentence,stringIdx,startWord-1));
                        answerList.append(" ");
                    }
                    answerList.append(makeWord(sentence,startWord,endWord));
                    answerList.append(" ");
                    lastStartWord = startWord;
                    lastEndWord = endWord;
                    lastStartChar = startChar;
                    lastEndChar = endChar;
                    stringIdx = endWord+1;
                }

                if(stringIdx < size){
                    answerList.append(makeWord(sentence,stringIdx,size-1));
                }
            }
            catch (Exception e){
                return invalid;
            }
            return answerList.toString().trim();
        }

        public String makeWord(String sentence, int start, int end){
            String word = sentence.substring(start, end+1);
            return word.replaceAll("[a-z]","");
        }
    }