import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        int[] vect = {-5, -4, -3, -2};

        // Teste da função com diferentes parâmetros
        List<int[]> result = menorDifAbsoluta(vect, false, true, true);
        for (int[] pair : result) {
            System.out.println("[" + pair[0] + ", " + pair[1] + "]");
        }
    }

    public static List<int[]> menorDifAbsoluta(int[] vect, boolean allowDuplicates, boolean sortedPairs, boolean uniquePairs) {
        List<int[]> list = new ArrayList<>();
        List<int[]> menorDiferenca = new ArrayList<>();

        // Calcular as diferenças absolutas entre os pares
        for (int i = 0; i < vect.length; i++) {
            for (int j = i + 1; j < vect.length; j++) {
                if (!allowDuplicates && vect[i] == vect[j]) {
                    continue; // Ignora pares com números duplicados se allowDuplicates for false
                }

                int a = vect[i];
                int b = vect[j];

                // Ordenar pares se sortedPairs for true
                if (sortedPairs && a > b) {
                    int temp = a;
                    a = b;
                    b = temp;
                }

                list.add(new int[]{a, b, Math.abs(a - b)});
            }
        }

        // Inicializa o menorValor com o primeiro valor na lista + 1
        int menorValor = list.get(0)[2] + 1;

        // Identifica o menor valor de diferença absoluta
        for (int[] pair : list) {
            if (pair[2] != 0 && pair[2] < menorValor) {
                menorValor = pair[2];
            }
        }

        // Filtra os pares que possuem a menor diferença
        for (int[] pair : list) {
            if (pair[2] == menorValor) {
                if (uniquePairs) {
                    // Verificar se o par (a, b) já existe na lista de menorDiferenca
                    boolean alreadyExists = false;
                    for (int[] existingPair : menorDiferenca) {
                        if (existingPair[0] == pair[0] && existingPair[1] == pair[1]) {
                            alreadyExists = true;
                            break;
                        }
                    }
                    if (!alreadyExists) {
                        menorDiferenca.add(new int[]{pair[0], pair[1]});
                    }
                } else {
                    menorDiferenca.add(new int[]{pair[0], pair[1]});
                }
            }
        }

        return menorDiferenca;
    }
}
