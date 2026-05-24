======================================================================
UNIVERSIDADE ABERTA - LICENCIATURA EM ENGENHARIA INFORMÁTICA
U.C. 21077 – Linguagens de Programação | e-Fólio B 2025/2026
======================================================================
Autor: Matheus Ferraz
Número de Estudante: 2401900
======================================================================

INSTRUÇÕES DE EXECUÇÃO (PowerShell)

1. Requisitos do Sistema
----------------------------------------------------------------------
* Java Development Kit (JDK) instalado.
* SWI-Prolog (64-bit) instalado no caminho padrão (C:\Program Files\swipl).

2. Como Correr o Sistema (Copiar e Colar)
----------------------------------------------------------------------
CENÁRIO A: EXECUTAR DIRETAMENTE (Usando a pasta /bin já incluída)
----------------------------------------------------------------------
O projeto já foi submetido com os ficheiros (.class) pré-compilados 
na pasta /bin para facilitar a correção. Não precisa de compilar.

Abra o terminal na raiz do projeto e execute conforme o seu OS:

--- No Windows (PowerShell ou CMD) ---
cd bin
java -cp ".;C:\Program Files\swipl\lib\jpl.jar" -Djava.library.path="C:\Program Files\swipl\bin" application.Aplicacao

--- No macOS (Terminal Unix) ---
cd bin
java -cp ".:/Applications/SWI-Prolog.app/Contents/Frameworks/plugins/jpl.jar" -Djava.library.path="/Applications/SWI-Prolog.app/Contents/MacOS" application.Aplicacao

--- No Linux / Ubuntu (Terminal Unix) ---
cd bin
java -cp ".:/usr/lib/swi-prolog/lib/jpl.jar" -Djava.library.path="/usr/lib/swi-prolog/bin" application.Aplicacao

----------------------------------------------------------------------
Nota: O projeto já inclui os ficheiros (.class) gerados na pasta /bin, 
pelo que não é necessária compilação manual.
----------------------------------------------------------------------
CENÁRIO B: SE DECIDIR APAGAR A PASTA /BIN E COMPILAR DO ZERO
----------------------------------------------------------------------
Se preferir gerar novos binários, apague a pasta /bin atual, abra o 
terminal na raiz do projeto e siga estes dois passos:

Passo 1: Compilar o código Java (gera a pasta /bin automaticamente)
Windows:
javac -cp ".;C:\Program Files\swipl\lib\jpl.jar" -d bin src/application/*.java

Mac / Linux:
javac -cp ".:/usr/lib/swi-prolog/lib/jpl.jar" -d bin src/application/*.java

Passo 2: Executar a aplicação recém-compilada
Utilize exatamente o comando correspondente ao seu OS listado acima 
no "CENÁRIO A" (entrando primeiro na pasta 'cd bin').
======================================================================
3. Estrutura do Projeto (Project Structure)
----------------------------------------------------------------------
📂 EfolioB2401900MatheusFerraz
 ├── 📄 motor_inferencia.pl       # Core inference engine and rules
 ├── 📄 base_conhecimento.pl      # Dynamic database facts
 ├── 📄 README.txt                # Este ficheiro
 ├── 📂 src
 │    └── 📂 application          # Source code (Java with EN comments)
 └── 📂 bin                       # Compiled bytecode target
======================================================================