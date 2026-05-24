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

2. Como Correr o Sistema (Multiplataforma)
----------------------------------------------------------------------
O projeto já inclui os binários pré-compilados na pasta /bin.

* No Windows (PowerShell):
  .\run.bat  (ou dê duplo clique no ficheiro run.bat)

* No macOS / Linux (Terminal):
  Dê permissão de execução e corra o script bash:
  chmod +x run.sh
  ./run.sh

3. Estrutura do Projeto (Project Structure)
----------------------------------------------------------------------
📂 EfolioB2401900MatheusFerraz
 ├── 📄 motor_inferencia.pl       # Core inference engine and rules
 ├── 📄 base_conhecimento.pl      # Dynamic database facts
 ├── 📄 readme.txt                # Este ficheiro
 ├── 📂 src
 │    └── 📂 application          # Source code (Java with EN comments)
 └── 📂 bin                       # Compiled bytecode target
======================================================================