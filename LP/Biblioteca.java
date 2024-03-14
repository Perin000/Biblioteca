import javax.swing.JOptionPane;
import java.util.ArrayList;

class livro {
    String titulolivro;
    String autor;
    int copiasDisponiveis;

    livro(String titulolivro, String autor, int copiasDisponiveis) {
        this.titulolivro = titulolivro;
        this.autor = autor;
        this.copiasDisponiveis = copiasDisponiveis;
    }
}

class emprestimo {
    String livroTitulo;
    String leitorNome;

    emprestimo(String livroTitulo, String leitorNome) {
        this.livroTitulo = livroTitulo;
        this.leitorNome = leitorNome;
    }
}

class Biblioteca {
    private static ArrayList<livro> acervo = new ArrayList<>();
    private static ArrayList<emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção:"));

            switch (escolha) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    realizarEmprestimo();
                    break;
                case 3:
                    exibirLivrosDisponiveis();
                    break;
                case 4:
                    exibirLivrosEmprestados();
                    break;
                case 5:
                    excluirlivro();
                    break;
                case 6:
                return;
                default:
                JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void exibirMenu() {
        JOptionPane.showMessageDialog(null,
        "1. Cadastrar Livro\n" +
        "2. Realizar Empréstimo\n" +
        "3. Livros Disponíveis\n" +
        "4. Livros Emprestados\n" +
        "5. Excluir Livro\n" +
        "6. Sair");
    }
    
    private static livro encontrarLivro(String titulo) {
        for (livro l : acervo) {
            if (l.titulolivro.equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }

    private static void cadastrarLivro() {
        String titulo = JOptionPane.showInputDialog("Digite o titulo do livro:");
        String autor = JOptionPane.showInputDialog("Digite o nome do autor:");
        int copias = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de copias disponiveis:"));

        livro l = new livro(titulo, autor, copias);
        acervo.add(l);

        JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
    }

    private static void realizarEmprestimo() {
        String leitorNome = JOptionPane.showInputDialog("Digite o nome do leitor:");
        String livroTitulo = JOptionPane.showInputDialog("Digite o titulo do livro a ser emprestado:");

        livro l = encontrarLivro(livroTitulo);

        if (l != null && l.copiasDisponiveis > 0) {
            l.copiasDisponiveis--;
            emprestimos.add(new emprestimo(livroTitulo, leitorNome));
            JOptionPane.showMessageDialog(null, "Emprestimo realizado com sucesso!");
        } 
        else {
            JOptionPane.showMessageDialog(null, "Livro não disponivel para emprestimo.");
        }
    }

    private static void exibirLivrosDisponiveis() {
        String mensagem = "Livros disponiveis:\n";
    
        for (livro l : acervo) {
            mensagem += l.titulolivro + " (" + l.copiasDisponiveis + " copias disponiveis)\n";
        }
    
        JOptionPane.showMessageDialog(null, mensagem);
    }
    

    private static void exibirLivrosEmprestados() {
        String mensagem = "Livros emprestados:\n";
    
        for (emprestimo e : emprestimos) {
            mensagem += "Leitor: " + e.leitorNome + ", Livro: " + e.livroTitulo + "\n";
        }
    
        JOptionPane.showMessageDialog(null, mensagem);
    }
    

    private static void excluirlivro() {
        String titulo = JOptionPane.showInputDialog("Digite o titulo do livro a ser excluido:");

        for (int i = 0; i < acervo.size(); i++) {
            if (acervo.get(i).titulolivro.equalsIgnoreCase(titulo)) {
                acervo.remove(i);
                JOptionPane.showMessageDialog(null, "Livro excluido com sucesso!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao excluir livro!");
            }
        }
    }

}
