import java.util.*;

public class Solution {
    public List<List<Integer>> execute(int[] coins, int sum) {
        return coinGame(coins, sum);
    }

    // solution
    private List<List<Integer>> coinGame(int[] coins, int sum) {
        // 중복 조합을 포함하지 않기 위해 정렬
        Arrays.sort(coins);

        // 메모이제이션
        Map<String, List<List<Integer>>> memo = new HashMap<>();

        // 탐색
        List<List<Integer>> answer = dfs(coins, sum, 0, memo);
        return answer;
    }

    private List<List<Integer>> dfs(int[] coins, int remain, int start, Map<String, List<List<Integer>>> memo) {
        // 조합을 찾은 경우
        if(remain == 0) {
            // 해당 조합을 담을 빈 리스트 반환
            List<List<Integer>> combination = new ArrayList<>();
            combination.add(new ArrayList<>()); // 빈 조합 하나 추가 (0원을 만드는 경우)
            return combination;
        }

        // 남은 금액에 대한 코인 조합이 있다면 바로 반환
        String key = remain + ":" + start;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // 남은 금액에 대한 코인 조합 탐색
        List<List<Integer>> result = new ArrayList<>();
        for(int i=start; i<coins.length; i++) {
            int coin = coins[i];
            if(remain < coin) break; // 남은 금액보다 코인 값이 크면 그 뒤는 탐색할 필요 x

            List<List<Integer>> combinations = dfs(coins, remain-coin, i, memo);

            for (List<Integer> combination : combinations) {
                combination.add(0, coin);
                result.add(new ArrayList<>(combination));
            }
        }
        memo.put(key, result);

        return result;
    }
}
