# Simulação de Dinâmica de População

![Github repo age](https://img.shields.io/github/issues/detail/age/bomrafinha/MyCollegeJobs/1.svg?style=flat-square)
![Github author](https://img.shields.io/github/issues/detail/u/bomrafinha/MyCollegeJobs/1.svg?style=flat-square)
![GitHub contributors](https://img.shields.io/github/contributors/bomrafinha/MyCollegeJobs)
![GitHub last commit](https://img.shields.io/github/last-commit/bomrafinha/MyCollegeJobs)
![GitHub commit activity](https://img.shields.io/github/commit-activity/y/bomrafinha/MyCollegeJobs.svg?style=flat-square)

![GitHub issues](https://img.shields.io/github/issues/bomrafinha/MyCollegeJobs)
![GitHub closed issues](https://img.shields.io/github/issues-closed/bomrafinha/MyCollegeJobs)
![GitHub pull requests](https://img.shields.io/github/issues-pr/bomrafinha/MyCollegeJobs)
![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed/bomrafinha/MyCollegeJobs)
![GitHub forks](https://img.shields.io/github/forks/bomrafinha/MyCollegeJobs)
![GitHub stars](https://img.shields.io/github/stars/bomrafinha/MyCollegeJobs)
![GitHub All Releases](https://img.shields.io/github/downloads/bomrafinha/MyCollegeJobs/total)

![GitHub top language](https://img.shields.io/github/languages/top/bomrafinha/MyCollegeJobs)
![GitHub language count](https://img.shields.io/github/languages/count/bomrafinha/MyCollegeJobs)
![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/bomrafinha/MyCollegeJobs)
![GitHub](https://img.shields.io/github/license/bomrafinha/MyCollegeJobs)

>
>Trabalho de Programação Concorrente - SIS0564A (UCS - Universidade de Caxias do Sul)
>

**Aluno:** Rafael Rossa</br>
**Instituição:** Universidade de Caxias do Sul</br>
**Curso:** Bacharelado em Ciência da Computação</br>
**Disciplina:** Programação Concorrente</br>
**Professor:** André Luiz Martinotto</br>
**Implementado em Java com IDE Netbeans**</br>

**Fórum: <a href="https://bomrafinha.dev/programacao-concorrente-simulador-populacional/" target="_blank">https://bomrafinha.dev/programacao-concorrente-simulador-populacional/</a>**</br></br>

[![Clique na Imagem para visualizar o vídeo](Documentacao/youtube_simuladorPoculacional_concorrente.png)](https://youtu.be/-hh5rUNwj9A "Clique na Imagem para visualizar o vídeo")

***Clique na Imagem para visualizar o vídeo***</br></br>

### Objetivo do Trabalho

O objetivo do trabalho consiste na implementação de um pequeno ecossistema formado por sapos, moscas e por açúcar.

### Descrição:

Depois de iniciar um programa, deverá ser apresentado um menu que permita adicionar moscas e sapos ao ambiente. O usuário poderá adicionar quantos sapos e moscas como desejar, sendo que esses indivíduos deverão ser gerados em posições aleatórias e deverão se movimentar aleatoriamente na tela, sem atravessar as bordas.
A quantidade de indivíduos e o tempo de simulação devem ser apresentados na tela. Os indivíduos deverão ser implementados como threads e compartilharão de um mesmo ambiente (como uma matriz, por exemplo).
A cada sapo é a associado um contador de calorias que é decrementado, sendo que o sapo morre quando o contador chegar a 0. Quando um sapo e uma mosca fizerem contato, a mosca morre e o contador de calorias do sapo é incrementado. O número de inicial calorias deverá ser fornecido pelo usuário no início do programa.
As moscas seguem o mesmo ciclo de vida de um sapo (mesmo número de calorias dos sapos), sendo que essas morrem de fome se não obterem comida (açúcar). Assim que uma mosca comer um lote de açúcar (ou seja, sua contagem de calorias exceder um limite pré-definido), ela irá se multiplicar automaticamente (ou seja, será gerado uma nova mosca). Já o açúcar deverá ser gerado aleatoriamente no ambiente e deverá ficar em posições fixas.

### Observações:
- Linguagens permitidas: Java, C#, C/C++, Python
- Grupos de no máximo dois alunos
- Trabalhos iguais serão zerados.

<br />

## Créditos <a name="creditos"></a>
[@bomrafinha](https://github.com/bomrafinha)

## [Licença](./LICENSE) <a name="licenca"></a>