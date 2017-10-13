library(shiny)
library(shinydashboard)
ui <- dashboardPage(
dashboardHeader(title ="Yield Management",
dropdownMenu(type = "notifications",
notificationItem(
text = "5 new users today",
icon("users")
),
notificationItem(
text = "12 items delivered",
icon("truck"),
status = "success"
),
notificationItem(
text = "Server load at 86%",
icon = icon("exclamation-triangle"),
status = "warning"
)
)
),
dashboardSidebar(
sidebarMenu(
menuItem("Market Overview", tabName = "overview", icon = icon("list-alt")),
menuItem("Dashboard", tabName = "dashboard", icon = icon("bar-chart-o")),
menuItem("Calender view", tabName = "calender", icon = icon("calendar") )
)
),
dashboardBody(
tags$head(tags$style(HTML('
      .main-header .logo {
        font-family: "Georgia", Times, "Times New Roman", serif;
        font-weight: bold;
        font-size: 24px;
      }
   '))),

tabItems(

# First tab content
tabItem(tabName = "overview",
h1("Market Overview"),
if (interactive())
{
    shinyUI(fluidPage(theme = "bootstrap.css",

    ui <- fluidPage(
    column(8, align="center", offset = 2,
    textInput("caption", "Property ID", "Enter Your PropertyID")),
    tags$style(type="text/css", "#string { height: 60px; width: 100%; text-align:center; font-size: 60px;}"),
    tags$style(type="text/css", "#string {table, th, td { border: 1px solid black; border: 1px solid black;}"),
    column(8, align="center", offset = 2,
    submitButton("Submit",icon("refresh"))))
    )
    )
  }),

#second tab content
tabItem(tabName = "dashboard",
h1("Dashboard"),


ui <- fluidPage(
column(8, align="center", offset = 2,
dateInput("date1", "Starting Date:", value = "2018-01-21"))



)),


# Second tab content
tabItem(tabName = "calender",
h1("Calender View"),

ui <- fluidPage(
column(8, align="center", offset = 2,
dateInput("date6", "Date:",
startview = "decade")),
tableOutput('table')

)


)
)
)
)




server <- function(input, output) {
    output$value <- renderText({input$caption })
    output$value <-renderPrint({input$num})
    output$selector <- renderUI({
        selectInput("bins",
        "Bins",
        choices = as.list(vector_choices),
        selected = 25)
    })

    output$prevBin <- renderUI({
        actionButton("prevBin",
        label = "Previous")
    })
    output$nextBin <- renderUI({
        actionButton("nextBin",
        label = "Next")
    })
    
    getCalendar <- function (){
      w1 <- c("1 Avg:Proce:2.3",8,3,4,5,"")
      w2 <- c(2,9,3,4,5,"")
      w3 <- c(3,10,3,4,5,"")
      w4 <- c(4,11,4,5,6,"")
      w5 <- c(5,12,3,4,5,"")
      w6 <- c(6,13,3,4,5,"")
      w7 <- c(7,14,3,4,5,"")
      data.f <- data.frame(sun = w1, mon=w2, tue=w3, wed=w4, thu=w5, fri=w6, sat=w7)
    }
    
    output$table <- renderTable( getCalendar())
}

shinyApp(ui, server)
