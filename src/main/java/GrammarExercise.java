import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = args[0];
        String secondWordList = args[1];

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //1.用正则表达式对input进行检查
        String regex = "^([a-zA-Z]+,)+[a-zA-Z]+";
        if (!firstWordList.matches(regex) || !secondWordList.matches(regex)){
            throw new RuntimeException("Input Not Valid!");
        }
        //2.创建Map表单统计各个单词的次数，合并流并整理出数据
        String[] firstWordArray = firstWordList.split(",");
        String[] secondWordArray = secondWordList.split(",");
        List<String> firstWord = Arrays.asList(firstWordArray);
        List<String> secondWord = Arrays.asList(secondWordArray);
        Stream<String> resultStream = Stream.concat(firstWord.stream(),secondWord.stream()).sorted();
        Map<String,Long> resultCount = resultStream.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        //3.获取只有一个的数据并加入到result中，整理排序result后，返回result
        List<String> result = new ArrayList<String>();
        Iterator iterator = resultCount.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,Long> resultPiece = (Map.Entry<String, Long>) iterator.next();
            if(resultPiece.getValue() > 1){
                result.add(resultPiece.getKey());
            }
        }
        Collections.sort(result);
        return result;
    }
}
