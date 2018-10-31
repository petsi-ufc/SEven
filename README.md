# SEven

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3a8af3814b274b31ba0a1fbb80032fb5)](https://app.codacy.com/app/MateusPinheiro/SEven?utm_source=github.com&utm_medium=referral&utm_content=petufc/SEven&utm_campaign=Badge_Grade_Dashboard)

## Configurações para o sistema SEven

### Preparação para a importação do SEven
- Antes de começarmos primeiro você deve instalar os seguintes programas:
  - Eclipse IDE ([Download](http://www.eclipse.org/downloads/))
  - Git ([Download](https://git-scm.com/downloads))
  - Java JDK 1.8 ([Download](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html))
- E baixar o arquivo .zip do servidor tomcat na versão 8.5 ([Download](https://tomcat.apache.org/download-80.cgi))

### 1° Passo: Clonando projeto do GIT

- Antes de tudo crie uma pasta dedicada para o **SEven** e abra o _git bash_ ou o _terminal_ no diretório dela.
- Após fazer isso digite o seguinte comando: `git clone https://github.com/petufc/SEven.git -b develop`

### 2° Passo: Importando projeto para o eclipse

- Após abrir o eclipe clique em _File_ e depois vá em _Open Projects from File System..._<br>
![Imgur](https://i.imgur.com/MMQrp6Z.png)

- Clique em _Directory_ e depois vá na pasta que acabou de ser clonada do GIT<br>
![Imgur](https://i.imgur.com/9ILxlpr.png)

- Certifique-se que a checkbox do _SEven_ está marcada e clique em _Finish_<br>
![Imgur](https://i.imgur.com/MmYmUdj.png)

Pronto agora seu projeto já foi importado para o eclipse agora nos proximos passos vamos configura-lo.

### 3° Passo: Instalando o Servidor Tomcat
- Digitando o atalho **CTRL + N** aparecerá uma tela para adicionarmos coisas, digite na barra de pesquisa _Server_ e clique na opção.<br>
![Imgur](https://i.imgur.com/PW4nhLo.png)

- Após isso digite Apache na barra de pesquisa, escolha a opção *Tomcat v8.5 Server* e clique em *Next*<br>
![Imgur](https://i.imgur.com/H6fBhcC.png)

- Na próxima tela escolha a JDK que você instalou e, após isso clique em browse e escolha o arquivo .zip do tomcat que você baixou e clique em *finish*.<br>
![Imgur](https://i.imgur.com/gKDXDS1.png)

### 4° Passo: Configurando BuildPath
- Primeiro clique com o botão direito no projeto e seleciona a opção *Build Path > Configure Build Path...* <br>
![Imgur](https://i.imgur.com/2XjUq4K.jpg)

- Agora vamos configurar o *JRE System Library*, selecione a opção, se ela não existir você pode clicar em *Add Library...* e adiciona-lá.
- Após selecionar a opção clique em *Edit...* para ir á próxima tela. <br>
![Imgur](https://i.imgur.com/JMtE7Bs.png)

- Após isso marque o radio e selecione no dropdown a JDK instalada e clique em *Apply*.
- Se não estiver aparecendo a JDK clique em *Installed JREs...* <br>
![Imgur](https://i.imgur.com/s93FjTk.png)

- Clique em *Search* e vá na pasta de instalação da sua JDK, selecione a pasta e marque a checkbox e depois clique em *Apply*. Após isso volte para o anterior e refaça.<br>
![Imgur](https://i.imgur.com/BJkoulc.png)

### 5° Passo: Configurando as *Project Facets*
- Agora vamos configurar as *Project Facets*, para isso digite o atalho **ALT + ENTER** e selecione no menu do lado esquerdo o *Project Facets*.
- Na tela das *Project Facets* selecione as facets mostradas na imagem.
  - *Dynamic Web Module* na versão 2.5
  - *Java* na versão 1.8
  - *JavaScript* na versão 1.0
![Imgur](https://i.imgur.com/HB0XAoj.png)

#### Após executar todos esses passos seu projeto estará configurado. Agora é o famoso "**Te vira NEGÃO**"
