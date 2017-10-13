ui <- dashboardPage(
  dashboardHeader(title = "Yield Management",
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
    tabItems(
  
      # First tab content
     tabItem(tabName = "overview",
          h1("Market Overview")
  ),
     #second tab content 
      tabItem(tabName = "dashboard",
         h1("avg of 30,60,90")
),      


      # Second tab content
      tabItem(tabName = "calender",
        h1("Calender View"),
        require(stats) # for rpois and xtabs
## Simple frequency distribution
table(rpois(100, 5))
## Check the design:
with(warpbreaks, table(wool, tension))
table(state.division, state.region)
   

      )
    )
)

  )


server <- function(input, output) { }

shinyApp(ui, server)
