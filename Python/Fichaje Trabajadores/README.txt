--------------------------------------
INTRODUCCIÓN (ESPAÑOL):
--------------------------------------
Para uqe funcionen las librerías que están insertadas en el código, 
hay que crear un entorno virtual e instalar las librerias en ese entorno virtual.

En este caso voy a explicar como hacerlo en visual studio code (ya que es la herramienta
que yo he utilizado).

--------------------------------------
PASOS A SEGUIR:
--------------------------------------
Paso 1: Para crear el entorno virtual dentro de una carpeta hay que utilizar este comando en
la terminal: 'python -m venv nombre_entorno_virtual'

Paso 2: Ahora hay que activar el entorno virtual. Se puede hacer desde el cmd o desde visual
studio code.
  - CMD: '.\env\Scripts\activate'
  - Visual Studio Code: Pulsa 'Ctrl + Shift + P (o F1)' para abrir la paleta de comandos.
    Escribe 'Phython: Select Interpreter' y selecciona esa opcion y elige tu entorno virtual.

    Si no detecta automáticamente la ruta pincha en "Enter interpreter path"
    Ahora tienes que establecer el entorno en 'settings.json' del proyecto, para eso pulsa '(Ctrl + ,)'
    En la barra lateral izquierda, haz clic en el icono de archivo '{}' para abrir el archivo de
    configuración JSON '(settings.json)'.

    Agrega o edita lo siguiente: 
    {
    "python.defaultInterpreterPath":
    "C:\\Users\\ljcal\\Documents\\programasPy\\env\\Scripts\\python.exe",
    "python.terminal.activateEnvironment": true
    }

    Para activar automáticamente el entorno virtual abre la configuración '(Ctrl + ,)' y busca
    "Activate Environment".
    Asegúrate de que la opción "Python › Terminal: Activate Environment" esté
    activada.
    
    Abre una nueva terminal en VSC (Ctrl + Ñ).
    Deberías ver que el entorno virtual se activa automáticamente mostrando algo
    como: (env) C:\Users\ljcal\Documents\programasPy>
    Puede que no aparezca el (env) pero VSC avisa de que está activado aún sin aparecer

Paso 3: Ahora que ya tienes creado el entorno virtual, toca instalar las librerías necesarias que
se utilizan en este proyecto.

  Paso 3.1: Instalar PyQt6
  Utiliza el comando 'pip install PyQt6' en la terminal. Si da problemas comprobar la versión de 
  Build Tools de Ms ('https://www.youtube.com/watch?v=olrAqUFvc5I'). Si sigue dando problemas utilizar
  este comando: 'pip install --index-url https://www.riverbankcomputing.com/pypi/simple/ 
  --no-deps --pre --upgrade PyQt6'

  Paso 3.2: Instalar PyQt6-tools
  Utiliza el comando 'pip install PyQt6-tools' en la terminal. Si da problemas, usar este comando:
  'pip install --index-url https://www.riverbankcomputing.com/pypi/simple/ --no-deps --pre --upgrade
  PyQt6-tools'

  Paso 3.3: Instalar Pyside6
  Utiliza el comando 'pip install Pyside6' en la terminal.

  Paso 3.4: Instalar pillow
  Utiliza el comando 'pip install pillow' en la terminal.

  Una vez instalado estas librerías, comprobar que se han instalado en el entorno virtual correctamente
  con el comando 'Pip list'.

  Tendría de salir algo como esto:
  Package             Version
  ------------------ ---------
  pillow              11.0.0
  pip                 24.3.1
  PyQt6               6.7.1
  PyQt6-Qt6           6.7.3
  PyQt6_sip           13.8.0
  pyqt6-tools         6.4.2.3.3
  PySide6             6.8.0.2
  PySide6_Addons      6.8.0.2
  PySide6_Essentials  6.8.0.2
  setuptools          75.6.0
  shiboken6           6.8.0.2
  wheel               0.45.1
-----------------------------------------------------------------------------------------------------

--------------------------------------
INTRODUCTION (ENGLISH):
--------------------------------------
For the libraries that are inserted in the code to work,
a virtual environment must be created, and the libraries must be installed in that virtual environment.

In this case, I am going to explain how to do it in Visual Studio Code (since it is the tool
that I have used).

--------------------------------------
STEPS TO FOLLOW:
--------------------------------------
Step 1: Step 1: To create the virtual environment inside a folder, you must use this command in
the terminal: 'python -m venv virtual_environment_name'

Step 2:Step 2: Now you must activate the virtual environment. It can be done from the CMD or from Visual
Studio Code.
  - CMD: '.\env\Scripts\activate'
  - Visual Studio Code: Press 'Ctrl + Shift + P (or F1)' to open the command palette. 
    Type 'Python: Select Interpreter' and select that option, then choose your virtual environment.

    If it does not automatically detect the path, click on "Enter interpreter path".
    Now you have to set the environment in the project´s 'settings.json', for that press '(Ctrl + ,)'.
    In the left sidebar, click on the {} file icon to open the JSON configuration file (settings.json).

    Add or edit the following:
    {
    "python.defaultInterpreterPath": "C:\\Users\\ljcal\\Documents\\programasPy\\env\\Scripts\\python.exe",
    "python.terminal.activateEnvironment": true
    }

    To automatically activate the virtual environment, open the settings '(Ctrl + ,)' and search for
    "Activate Environment".
    Make sure that the "Python › Terminal: Activate Environment" option is enabled.

    Open a new terminal in VSC '(Ctrl + Ñ)'.
    You should see that the virtual environment activates automatically, displaying something
    like: '(env) C:\Users\ljcal\Documents\programasPy>'
    It may not show (env), but VSC notifies that it is activated even if it does not appear.


Step 3: Now that you have created the virtual environment, it is time to install the necessary libraries that
are used in this project.

  Step 3.1: Install PyQt6
  Use the command 'pip install PyQt6' in the terminal. If it gives problems, check the version of
  Build Tools from Microsoft ('https://www.youtube.com/watch?v=olrAqUFvc5I'). If it still gives problems, use
  this command: 'pip install --index-url https://www.riverbankcomputing.com/pypi/simple/ --no-deps --pre 
  --upgrade PyQt6'

  Step 3.2: Install PyQt6-tools
  Use the command 'pip install PyQt6-tools' in the terminal. If it gives problems, use this command:
  'pip install --index-url https://www.riverbankcomputing.com/pypi/simple/ --no-deps --pre --upgrade PyQt6-tools'

  Step 3.3: Install Pyside6
  Use the command 'pip install Pyside6' in the terminal.

  Step 3.4: Install Pillow
  Use the command 'pip install pillow' in the terminal.

Once these libraries are installed, check that they have been installed in the virtual environment correctly
with the command 'pip list'.

It should show something like this:
Package             Version
------------------ ---------
pillow              11.0.0
pip                 24.3.1
PyQt6               6.7.1
PyQt6-Qt6           6.7.3
PyQt6_sip           13.8.0
pyqt6-tools         6.4.2.3.3
PySide6             6.8.0.2
PySide6_Addons      6.8.0.2
PySide6_Essentials  6.8.0.2
setuptools          75.6.0
shiboken6           6.8.0.2
wheel               0.45.1
                                                         
