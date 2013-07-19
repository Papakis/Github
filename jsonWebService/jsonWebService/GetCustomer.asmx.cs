using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Script.Serialization;

namespace jsonWebService
{
    /// <summary>
    /// This is a test json service in order to test it 
    /// with Javascript. At the moment it contains two methods 
    /// 1) GetGustomers receiving every customer from DB
    /// 2) GetGustomerById receiving customers according to the supplied ID
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
     
    [System.Web.Script.Services.ScriptService]
    public class GetCustomer : System.Web.Services.WebService
    {
        CustomersDataContext dcCustomers = new CustomersDataContext();

        string json = "";

        [WebMethod]
        public string GetGustomers()
        {
            var customers = from dcResults in dcCustomers.Customers select dcResults;
            JavaScriptSerializer jss = new JavaScriptSerializer();
            json = jss.Serialize(customers);
            return json;
        }

        [WebMethod]
        public string GetGustomerById(string id)
        {
            var customers = from dcResults in dcCustomers.Customers 
                            where dcResults.CustomerID == id
                            select dcResults;
            JavaScriptSerializer jss = new JavaScriptSerializer();
            json = jss.Serialize(customers);
            return json;
        }
    }
}
