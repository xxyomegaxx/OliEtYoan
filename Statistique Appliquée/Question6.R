## A.##
library(readxl)
achats <- read_excel("Documents/Oli_et_Yoan/Statistique Appliquée/achats.xlsx")
View(achats)

#Extraction des données de la colonne Type
Type = achats['Type']

#Array to list
Type <- c(Type)

#Creating a table with a type list
w = table(Type)

#Creating a data frame 
t = as.data.frame(w)
#Showing t
t

slices <- t['Freq']

## B ##


# Define some colors 
colors <- c("yellow2","olivedrab3","orangered3","red")

# Calculate the percentage for each day, rounded to one decimal place
slices_labels <- round(slices/sum(slices) * 100, 1)

# Concatenate a '%' char after each value
slices_labels <- paste(slices_labels, "%", sep="")

# Create a pie chart with defined heading and custom colors and labels
pie(slices, main="Sum", col=colors, labels=slices_labels, cex=0.8)

# Create a legend at the right   
#legend(.1, c("Vetement","Billets","Autres","Electro"), cex = 0.7, fill = colors)




