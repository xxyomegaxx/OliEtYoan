## A.##
library(readxl)
achats <- read_excel("Documents/Oli_et_Yoan/Statistique Appliquée/achats.xlsx")

#Extraction des données de la colonne Type
Type = achats$Type
#Array to list
Type <- c(Type)
#Creating a table with a type list
w = table(Type)

#Creating a data frame 
t = as.data.frame(w)
#Showing t
t

## B ##
slices <- t$Freq
lbls <- t$Type

pct <- round(slices/sum(slices)*100, 1)
lbls <- paste(lbls," (",pct,"%) ", sep="")

pie(pct,lbls, main = "Fréquence relative de chaque Type d'achat en ligne")

## C ## 
nbTransaction <- achats$Transactions
moyenne <- mean(nbTransaction)
moyenne
ecart <- sd(nbTransaction)
ecart

## D ##
hist(achats$Montant,  ylim=c(0,120), xlim =c(0,5000),ylab="Fréquence", xlab="Montant", main = "La fréquence du montant total dépensé")

## E ##
montant = achats$Montant
transaction = achats$Transactions

donnees<-data.frame(transaction,montant)

plot(transaction,montant, xlab="Nombre de transaction", ylab="montant", main="Variation du nombre de transaction par rapport au montant")

reg<-lm(montant~transaction) #Cr�ation du mod�le lin�aire
summary(reg) #Quelques donn�es sur le mod�le de r�gression lin�aire
abline(reg, col='red') #Ajout de la droite de r�gression au mod�le

## F ##
paste('y =', coef(reg)[[2]], '* x', '+', coef(reg)[[1]])

## G ## 
summary(reg)$r.squared

