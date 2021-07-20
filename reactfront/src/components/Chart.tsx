import { useEffect } from 'react'
import { useState } from 'react'
import {Line} from 'react-chartjs-2'
import api from '../api/api'
import {ResponsiveContainer,
        AreaChart,
    XAxis,YAxis,
Area,Tooltip,CartesianGrid} from 'recharts'

interface ChartData{
    labels:string[],
    datasets:axiosy[]
}

interface axiosy{
    label:string,
    data:number[],
}

interface ChartProps {
    stockname:string
}

export function Chart(stock:ChartProps){
    const [chartData,setChartData]=useState<ChartData>()
    let dates:string[]=[];
    let prices:number[]=[];
    useEffect(()=>{
        const interval=setInterval(async ()=>{
            api.get('/api/chart?stock='+stock.stockname)
        .then(res=>{
            dates=[]
            prices=[]
            for(const obj of res.data){
                dates.push(obj.date)
                prices.push(obj.price)
            }
        })
        .catch(err=>console.log(err))
        console.log(dates)
        console.log(prices)

        setChartData({
            labels:dates,
            datasets:[{label:"Stock Price",data:prices}]
          }) 
        },7000)
        return ()=>clearInterval(interval)                 
    },[])
        return (
        <Line type="line" data={chartData}/>
    )
}