class_d=bin
source_d=src

JAVAC=javac
FLAGS=-d $(class_d) -sourcepath $(source_d) -Xlint:all


S_VUES= $(shell find $(source_d)/com/vues -name *.java)
C_VUES= $(patsubst src/%.java, bin/%.class, $(S_VUES))

S_MODELES= $(shell find $(source_d)/com/modeles -name *.java)
C_MODELES= $(patsubst src/%.java, bin/%.class, $(S_MODELES))

S_CONTROLEURS= $(shell find $(source_d)/com/controleurs -name *.java)
C_CONTROLEURS= $(patsubst src/%.java, bin/%.class, $(S_CONTROLEURS))

S_COM= $(shell find $(source_d)/com -name *.java)
C_COM= $(patsubst src/%.java, bin/%.class, $(S_COM))

S_RES= $(shell find $(source_d)/com/res -name *.png)
C_RES= $(patsubst src/%.png, bin/%.png, $(S_RES))

.SUFFIXES: .java .class

default: classes

$(class_d)/%.class: $(source_d)/%.java
	@$(JAVAC) $(FLAGS) $<;

$(class_d)/%.png: $(source_d)/%.png
	@mkdir -p $(@D)
	@cp $< $@

classes: $(class_d) res controleurs modeles vues com

vues: $(C_VUES)
controleurs: $(C_CONTROLEURS)
modeles: $(C_MODELES)
com: $(C_COM)
res: $(C_RES)

all: classes

$(class_d):
	@mkdir $(class_d)

javadoc:
	javadoc -d doc -encoding "ISO-8859-1" -docencoding "ISO-8859-1" -charset "ISO-8859-1" -classpath src -subpackages com:com.vues:com.modeles:com.controleurs

clean:
	rm -rf $(class_d)
